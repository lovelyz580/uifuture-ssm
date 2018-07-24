package uifuture.ssm.dao;

import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: �º���.
 * Date: 2017/3/18.
 * Time: ���� 9:53.
 * Explain:�������ݿ�־û���������
 */
@Repository
public interface BaseMapping<T> {
    /**
     * ����ID��ѯ����
     *
     * @param id ʵ�����ID
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * ִ����ȫ������� Ҳ���ǲ�����nullֵ
     *
     * @param entity
     * @return
     */
    Integer insert(T entity);

    /**
     * ִ�в��ֲ������ Ҳ����nullֵ������
     *
     * @param entity
     * @return
     */
    Integer insertSelective(T entity);

    /**
     * ִ�и��²��� ȫ������
     *
     * @param entity
     * @return
     */
    Integer updateByPrimaryKey(T entity);

    /**
     * ִ�в��ָ��²��� Ϊnull�Ĳ��ᱻ����
     *
     * @param entity
     * @return
     */
    Integer updateByPrimaryKeySelective(T entity);

    /**
     * ���������ϵ�ɾ��
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

}
