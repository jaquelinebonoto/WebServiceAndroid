package com.ssi.correio.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ssi.correio.model.CEP;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;


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
        //stringbuilder para receber o json
        StringBuilder resposta = new StringBuilder();

        if (this.cep != null && this.cep.length() == 8) {
            try{
                //url da api
                URL url = new URL("http://viacep.com.br/ws/" + this.cep + "/json/");
                //configuração do protocolo
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                //a conexão de fato
                connection.connect();

                //recebendo o retorno
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //transformando e json string em cep
        return new Gson().fromJson(resposta.toString(), CEP.class);
    }
}
