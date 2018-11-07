package br.com.bicmsystems.ondeeh.view.pesquisa

import br.com.bicmsystems.ondeeh.base.BasePresenter
import br.com.bicmsystems.ondeeh.network.EnderecoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PesquisaPresenter(pesquisaView: PesquisaView) :
            BasePresenter<PesquisaView>(pesquisaView) {

    @Inject
    lateinit var enderecoAPI: EnderecoAPI

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        super.onViewCreated()
    }

    override fun onViewDestroyed() {

        super.onViewDestroyed()
        subscription?.dispose()

    }

    fun pesquisar(cep: String) {

        view.showLoading()

        subscription = enderecoAPI
                .pesquisar(cep)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { endereco -> view.atualizaEndereco(endereco) },
                        { error -> view.showError("Erro: ${error}") }
                )

    }

}