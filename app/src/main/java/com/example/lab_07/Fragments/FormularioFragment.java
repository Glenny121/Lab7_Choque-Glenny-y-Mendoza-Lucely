package com.example.lab_07.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.lab_07.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FormularioFragment extends Fragment {

    private EditText edtAutor, edtTitulo, edtTecnica, edtDescripcion, edtFecha;
    private RadioGroup opCategoria;
    private Button btnGuardar;

    public FormularioFragment() {
        // Required empty public constructor
    }

    public static FormularioFragment newInstance() {
        return new FormularioFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);

        edtAutor = view.findViewById(R.id.edtAutor);
        edtTitulo = view.findViewById(R.id.edtTitulo);
        edtTecnica = view.findViewById(R.id.edtTecnica);
        edtDescripcion = view.findViewById(R.id.edtDescripcion);
        edtFecha = view.findViewById(R.id.edtFecha);
        opCategoria = view.findViewById(R.id.opCategoria);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    guardarDatos();
                }
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            guardarDatos();
        } else {
            Toast.makeText(getActivity(), "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarDatos() {
        String autor = edtAutor.getText().toString();
        String titulo = edtTitulo.getText().toString();
        String tecnica = edtTecnica.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String fecha = edtFecha.getText().toString();
        int selectedId = opCategoria.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(selectedId);
        String categoria = radioButton != null ? radioButton.getText().toString() : "";

        String data = "Autor: " + autor + "\n" +
                "Título: " + titulo + "\n" +
                "Técnica: " + tecnica + "\n" +
                "Categoría: " + categoria + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Fecha: " + fecha + "\n";

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "datos_pintura.txt");


        try {
            FileOutputStream fos = new FileOutputStream(file, true); // true para añadir al archivo existente
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getActivity(), "Datos guardados", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
        // Después de FileOutputStream
        Log.d("Archivo", "Path: " + path.getAbsolutePath());
        if (file.exists()) {
            Log.d("Archivo", "Archivo existe en la ruta especificada.");
        } else {
            Log.d("Archivo", "No se pudo crear el archivo.");
        }
    }
}
