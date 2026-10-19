package com.example.asamid1409_szachownicaimenucontext;

import static java.lang.Math.min;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MojWidokSzachownicy widokMojejSzachownicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        widokMojejSzachownicy = new MojWidokSzachownicy(this);
        setContentView(widokMojejSzachownicy);

        registerForContextMenu(widokMojejSzachownicy);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
        menu.setHeaderTitle("Opcje Szachownicy");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int wybranyIdentyfikatorElementu = item.getItemId();

        if (wybranyIdentyfikatorElementu == R.id.kolor1) {
            Toast.makeText(this, "Zmieniono motyw kolorystyczny", Toast.LENGTH_SHORT).show();
            return true;
        } else if (wybranyIdentyfikatorElementu == R.id.kolor2) {
            Toast.makeText(this, "Zresetowano widok", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private class MojWidokSzachownicy extends View {
        private Paint mojMalarzPol;

        public MojWidokSzachownicy(Context context) {
            super(context);
            mojMalarzPol = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int aktualnaSzerokoscOkna = getWidth();
            int aktualnaWysokoscOkna = getHeight();

            int bezpiecznyBokSzachownicy = min(aktualnaSzerokoscOkna, aktualnaWysokoscOkna);
            int rozmiarJednegoPola = bezpiecznyBokSzachownicy / 8;

            int marginesX = (aktualnaSzerokoscOkna - bezpiecznyBokSzachownicy) / 2;
            int marginesY = (aktualnaWysokoscOkna - bezpiecznyBokSzachownicy) / 2;

            for (int wiersz = 0; wiersz < 8; wiersz++) {
                for (int kolumna = 0; kolumna < 8; kolumna++) {

                    if ((wiersz + kolumna) % 2 == 0) {
                        mojMalarzPol.setColor(Color.WHITE);
                    } else {
                        mojMalarzPol.setColor(Color.BLACK);
                    }

                    int lewy = marginesX + (kolumna * rozmiarJednegoPola);
                    int gorny = marginesY + (wiersz * rozmiarJednegoPola);
                    int prawy = lewy + rozmiarJednegoPola;
                    int dolny = gorny + rozmiarJednegoPola;

                    canvas.drawRect(lewy, gorny, prawy, dolny, mojMalarzPol);
                }
            }
        }
    }
}
