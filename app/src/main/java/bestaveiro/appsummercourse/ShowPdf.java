package bestaveiro.appsummercourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class ShowPdf extends AppCompatActivity {

    int option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf);
        Intent intent= getIntent();
        Bundle bd=intent.getExtras();
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        if(bd!=null){
            int getInt= (int) bd.get("i");
            switch (getInt){
                case 0:
                    pdfView.fromAsset("example.pdf").load();
                    break;
                case 1:
                    pdfView.fromAsset("example2.pdf").load();
                    break;
                case 2:
                    pdfView.fromAsset("example.pdf").load();
                    break;
                case 3:
                    pdfView.fromAsset("example2.pdf").load();
                    break;
            }
        }



    }
}
