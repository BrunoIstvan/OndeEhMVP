package br.com.bicmsystems.ondeeh.view.pesquisa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.bicmsystems.ondeeh.R
import br.com.bicmsystems.ondeeh.base.BaseActivity
import br.com.bicmsystems.ondeeh.model.Endereco
import kotlinx.android.synthetic.main.activity_pesquisa.*

class PesquisaActivity : BaseActivity<PesquisaPresenter>(), PesquisaView {

    override fun instantiatePresenter(): PesquisaPresenter {
        return PesquisaPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)
        //presenter.pesquisar("03282000")
        btnPesquisar.setOnClickListener {
            if(edtCEP.text.isNullOrEmpty()) {
                Toast.makeText(this, "Informe um CEP", Toast.LENGTH_LONG).show()
            } else {
                presenter.pesquisar(edtCEP.text!!.toString())
            }
        }

    }

    override fun atualizaEndereco(endereco: Endereco) {

        tvLogradouro.setText(endereco.logradouro)
        tvComplemento.setText(endereco.complemento)
        tvBairro.setText(endereco.bairro)
        tvCidade.setText(endereco.localidade)
        tvEstado.setText(endereco.uf)
        tvCEP.setText(endereco.cep)
        //Toast.makeText(this, endereco.logradouro, Toast.LENGTH_LONG).show()

    }

    override fun showError(error: String) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}
