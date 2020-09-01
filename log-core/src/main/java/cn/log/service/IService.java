package cn.log.service;

public interface IService<T,S> {
    T selectById(S id);
}
