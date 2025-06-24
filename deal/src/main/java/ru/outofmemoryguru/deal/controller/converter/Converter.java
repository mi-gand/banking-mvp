package ru.outofmemoryguru.deal.controller.converter;

public interface Converter<O,S> {
    O convertToDto(S source);
    S convertToServiceModel(O source);
}
