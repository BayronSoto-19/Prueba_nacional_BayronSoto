package com.example.prueba_nacional_bayronsoto.Vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba_nacional_bayronsoto.Controlador.Usuario;
import com.example.prueba_nacional_bayronsoto.R;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText Nombre, Edad, Correo, Contraseña2;
    Button registrar, limpiar, loguer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Nombre = findViewById(R.id.txtnombre);
        Edad = findViewById(R.id.txtedad);
        Correo = findViewById(R.id.txtcorreo2);
        Contraseña2 = findViewById(R.id.txtcontraseña2);

        registrar = findViewById(R.id.btnRegistrar1);
        limpiar = findViewById(R.id.btnclean);
        loguer = findViewById(R.id.btnLogin1);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Nombre.getText().toString().trim().isEmpty()
                        || Edad.getText().toString().trim().isEmpty()
                        || Correo.getText().toString().trim().isEmpty()
                        || Contraseña2.getText().toString().trim().isEmpty()) {

                    Toast.makeText(Register.this, "Atencion, complrete los campos que le restan", Toast.LENGTH_SHORT).show();
                }

                else {
                    String nombre = Nombre.getText().toString();
                    int Edad2 = Integer.parseInt(Edad.getText().toString());
                    String correo = Correo.getText().toString();
                    String contraseña2 = Contraseña2.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Usuario usuario = new Usuario(nombre, Edad2, correo, contraseña2);
                            dbref.push().setValue(usuario);
                            Toast.makeText(Register.this, "Atencion, no se a podido ingresar el usuario correctamente", Toast.LENGTH_SHORT).show();
                            Nombre.setText("");
                            Edad.setText("");
                            Correo.setText("");
                            Contraseña2.setText("");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}