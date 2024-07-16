package br.com.alura.literalura.service;

public interface IConversorJson {
    <T> T mesclarJson(String json, Class<T> classe);
}
