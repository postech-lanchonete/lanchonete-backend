package br.com.lanchonetebairro.core.applications.usecases;

public interface UseCase<E, S> {
    S realizar(E entrada);

    interface NoInputUseCase<S> extends UseCase<Void, S> {
    }
}
