package br.com.lanchonetebairro.applicationrules.usecases;

public interface UseCase<E, S> {
    S realizar(E entrada);
}
