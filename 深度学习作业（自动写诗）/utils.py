# -*- coding: utf-8 -*-
# @Time    : 19-4-13 下午2:50
# @Author  : Tom
# @Email   : w527589172@163.com
import setting

def read_word_list():
    """
    从文件读取词汇表
    :return: 词汇列表
    """
    with open(setting.VOCAB_PATH, 'r',encoding='GBK') as f:
        word_list = [word for word in f.read().strip().split('\n')]
    return word_list

def read_word_to_id_dict():
    """
    生成单词到id的映射
    :return:
    """
    word_list=read_word_list()
    word2id=dict(zip(word_list,range(len(word_list))))
    return word2id

def read_id_to_word_dict():
    """
    生成id到单词的映射
    :return:
    """
    word_list=read_word_list()
    id2word=dict(zip(range(len(word_list)),word_list))
    return id2word


if __name__ == '__main__':
    read_id_to_word_dict()