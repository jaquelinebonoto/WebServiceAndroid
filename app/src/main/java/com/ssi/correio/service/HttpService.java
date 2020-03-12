package com.ssi.correio.service;

import android.os.AsyncTask;

import com.ssi.correio.model.CEP;

import java.net.MalformedURLException;
import java.net.URL;


//parametros de AsyncTask:
//1. enviado para a execução da classe
//2. tipo de parametro no método onProgressUpdate
//3. tipo do retorno da classe
public class HttpService extends AsyncTask<Void, Void, CEP> {

    //instanciando o cep e colocando dentro do construtor
    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }
    @Override
    protected CEP doInBackground(Void... voids) {
        if (this.cep != null && this.cep.length() == 8) {
            try{
                URL url = new URL("http://viacep.com.br/ws/" + this.cep + "/json/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
