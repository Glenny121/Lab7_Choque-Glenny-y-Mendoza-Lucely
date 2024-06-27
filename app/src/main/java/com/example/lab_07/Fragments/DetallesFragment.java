package com.example.lab_07.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lab_07.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DetallesFragment extends Fragment {
    private TextView tvAutor, tvTitulo, tvTecnica, tvCategoria, tvDescripcion, tvFecha;

    public DetallesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);

        tvAutor = view.findViewById(R.id.tvAutor);
        tvTitulo = view.findViewById(R.id.tvTitulo);
        tvTecnica = view.findViewById(R.id.tvTecnica);
        tvCategoria = view.findViewById(R.id.tvCategoria);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        tvFecha = view.findViewById(R.id.tvFecha);

        mostrarUltimaPintura();

        return view;
    }

    private void mostrarUltimaPintura() {
        File path = new File("/storage/emulated/0/Documents");
        File file = new File(path, "datos_pintura.txt");

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                // Aquí parseas el contenido del archivo y lo muestras en los TextView correspondientes
                String[] datos = stringBuilder.toString().split("\n");

                for (String dato : datos) {
                    if (dato.startsWith("Autor: ")) {
                        tvAutor.setText(dato);
                    } else if (dato.startsWith("Título: ")) {
                        tvTitulo.setText(dato);
                    } else if (dato.startsWith("Técnica: ")) {
                        tvTecnica.setText(dato);
                    } else if (dato.startsWith("Categoría: ")) {
                        tvCategoria.setText(dato);
                    } else if (dato.startsWith("Descripción: ")) {
                        tvDescripcion.setText(dato);
                    } else if (dato.startsWith("Fecha: ")) {
                        tvFecha.setText(dato);
                    }
                }

                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Manejo de errores al leer el archivo
            }
        } else {
            // Manejo si el archivo no existe
        }
    }
}
