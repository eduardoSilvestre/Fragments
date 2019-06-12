package br.com.digitalhouse.fragmentsproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachFragments(new PrimeiroFragment(), new SegundoFragment());

        Button primeiroButton = findViewById(R.id.primeiro_fragment_button);
        Button segundoButton = findViewById(R.id.segundo_fragment_button);
        final Button inverterFragment = findViewById(R.id.inverter_fragment_button);


        primeiroButton.setOnClickListener(new View.OnClickListener()
    {
            @Override
            public void onClick(View view) {
                exibirPrimeiroFragment();
            }
        });

        segundoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirSegundoFragment();
            }
        });

        inverterFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inverterOsFragments();
            }
        });

    }

    public void exibirPrimeiroFragment(){
        attachFragments(new PrimeiroFragment(), new PrimeiroFragment());
    }

    public void exibirSegundoFragment(){

        attachFragments(new SegundoFragment(), new SegundoFragment());

    }

    public void inverterOsFragments(){

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        Fragment fragmentSuperior = getFragmentInverso(fragments.get(0));
        Fragment fragmentInferior = getFragmentInverso(fragments.get(1));
        attachFragments(fragmentSuperior, fragmentInferior);
    }

    private Fragment getFragmentInverso(Fragment fragment){
        if (fragment instanceof PrimeiroFragment){
            return new SegundoFragment();
        } else {
            return new PrimeiroFragment();
        }
    }


    private void attachFragments(Fragment fragmentSuperior, Fragment fragmentInferior){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.outro_container_id, fragmentSuperior);
        transaction.replace(R.id.container_id, fragmentInferior);

        transaction.commit();

    }



}
