package cn.zyt.springbootlearning.ioc.injection;

public interface Person {
    /**
     * 人依赖于动物提供服务
     */
    void service();

    /**
     * 设置依赖的动物
     */
    void setAnimal(Animal animal);
}
