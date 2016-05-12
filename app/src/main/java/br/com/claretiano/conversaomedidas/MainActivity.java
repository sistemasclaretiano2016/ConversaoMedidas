package br.com.claretiano.conversaomedidas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener, TextWatcher {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private RadioGroup rdbConversao;

    private Spinner spnDe;
    private Spinner spnAte;

    private EditText edtValor;

    private TextView txtResultado;

    private List<String> listaItensDe = new ArrayList<>();
    private List<String> listaItensPara = new ArrayList<>();

    private TipoConversaoEnum tipoConversaoEnum = TipoConversaoEnum.None;


    private void preencherListaComprimento(String selecionado) {
        if (selecionado == null || selecionado.equals(""))
            selecionado = "Metro";

        listaItensDe.clear();
        listaItensDe.add("Metro");
        listaItensDe.add("Polegada");
        listaItensDe.add("Quilometro");

        preencherSppinerDe();
        preencherListaAte(selecionado);
    }

    private void preencherListaPeso(String selecionado) {
        if (selecionado == null || selecionado.equals(""))
            selecionado = "Quilograma";

        listaItensDe.clear();
        listaItensDe.add("Quilograma");
        listaItensDe.add("Grama");
        listaItensDe.add("Tonelada");

        preencherSppinerDe();
        preencherListaAte(selecionado);
    }

    private void preencherListaTemperatura(String selecionado) {
        if (selecionado == null || selecionado.equals(""))
            selecionado = "Fahrenheit";

        listaItensDe = new ArrayList<>();
        listaItensDe.add("Fahrenheit");
        listaItensDe.add("Celsius");

        preencherSppinerDe();
        preencherListaAte(selecionado);
    }

    private void preencherSppinerDe() {
        ArrayAdapter<String> adapterDe = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaItensDe
        );

        spnDe.setAdapter(adapterDe);
    }

    private void preencherListaAte(String selecionado) {
        listaItensPara.clear();
        listaItensPara.addAll(listaItensDe);
        listaItensPara.remove(selecionado);

        preencherSpinnerAte();
    }

    private void preencherSpinnerAte() {
        ArrayAdapter<String> adapterAte = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaItensPara
        );

        spnAte.setAdapter(adapterAte);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdbConversao = (RadioGroup) findViewById(R.id.rdg_grupo);
        if (rdbConversao != null)
            rdbConversao.setOnCheckedChangeListener(this);

        spnDe = (Spinner) findViewById(R.id.spn_de);
        spnAte = (Spinner) findViewById(R.id.spn_ate);

        edtValor = (EditText) findViewById(R.id.edt_valor);
        txtResultado = (TextView) findViewById(R.id.txt_resultado);

        spnDe.setOnItemSelectedListener(this);
        spnAte.setOnItemSelectedListener(this);

        edtValor.addTextChangedListener(this);

        tipoConversaoEnum = TipoConversaoEnum.Comprimento;

        preencherListaComprimento("");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rdb_comprimento) {
            tipoConversaoEnum = TipoConversaoEnum.Comprimento;

            preencherListaComprimento("");
        } else if (checkedId == R.id.rdb_peso) {
            tipoConversaoEnum = TipoConversaoEnum.Peso;

            preencherListaPeso("");
        } else if (checkedId == R.id.rdb_temperatura) {
            tipoConversaoEnum = TipoConversaoEnum.Temperatura;

            preencherListaTemperatura("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spn_de) {
            preencherListaAte(listaItensDe.get(position));
            txtResultado.setText("");
        } else if (parent.getId() == R.id.spn_ate) {
            txtResultado.setText("");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            String resultado = "";
            double valor = 0;
            try {
                valor = Double.parseDouble(s.toString());
            } catch (Exception ignored) {
                valor = 0;
            }

            if (valor <= 0) {
                txtResultado.setText("");
                return;
            }

            if (TipoConversaoEnum.Comprimento.equals(tipoConversaoEnum)) {
                resultado = String.format(Locale.getDefault(), "%f %ss",
                        UtilConversao.ConverterComprimento(
                                UtilConversao.ComprimentoEnum.retornaPorLegenda(listaItensDe.get(spnDe.getSelectedItemPosition())),
                                UtilConversao.ComprimentoEnum.retornaPorLegenda(listaItensPara.get(spnAte.getSelectedItemPosition())),
                                valor),
                        listaItensPara.get(spnAte.getSelectedItemPosition()));
            } else if (TipoConversaoEnum.Peso.equals(tipoConversaoEnum)) {
                resultado = String.format(Locale.getDefault(), "%f %ss",
                        UtilConversao.ConverterPeso(
                                UtilConversao.PesoEnum.retornaPorLegenda(listaItensDe.get(spnDe.getSelectedItemPosition())),
                                UtilConversao.PesoEnum.retornaPorLegenda(listaItensPara.get(spnAte.getSelectedItemPosition())),
                                valor),
                        listaItensPara.get(spnAte.getSelectedItemPosition()));
            } else if (TipoConversaoEnum.Temperatura.equals(tipoConversaoEnum)) {
                resultado = String.format(Locale.getDefault(), "%f %s",
                        UtilConversao.ConverterTemperatura(
                                UtilConversao.TemperaturaEnum.retornaPorLegenda(listaItensDe.get(spnDe.getSelectedItemPosition())),
                                UtilConversao.TemperaturaEnum.retornaPorLegenda(listaItensPara.get(spnAte.getSelectedItemPosition())),
                                valor),
                        listaItensPara.get(spnAte.getSelectedItemPosition()));
            }

            txtResultado.setText(resultado);
        } catch (Exception ex) {
            Toast.makeText(this, String.format("Erro ao tentar converter.\n%s", ex.getMessage()), Toast.LENGTH_SHORT).show();
            txtResultado.setText("");
        }
    }


    public enum TipoConversaoEnum {None, Comprimento, Peso, Temperatura}
}
