# 数据结构(严蔚敏版)算法实现--java版本
<!-- TOC -->

- [数据结构(严蔚敏版)算法实现--java版本](#数据结构严蔚敏版算法实现--java版本)
    - [1. 字符串匹配](#1-字符串匹配)
        - [1.1 算法实现](#11-算法实现)
        - [1.2 复杂度分析](#12-复杂度分析)
    - [2. 矩阵的压缩存储](#2-矩阵的压缩存储)
        - [2.1 理论依据](#21-理论依据)
        - [2.2 快速转置实现](#22-快速转置实现)
        - [2.3 复杂度分析](#23-复杂度分析)
    - [2. huffman树及其应用](#2-huffman树及其应用)
        - [2.1 树的构造以及编解码实现](#21-树的构造以及编解码实现)
        - [2.2 构造方法](#22-构造方法)
        - [2.3 编码方法](#23-编码方法)
        - [2.4 解码方法](#24-解码方法)

<!-- /TOC -->

## 1. 字符串匹配

### 1.1 算法实现

[点我查看 KMP算法 实现](./src/main/java/tech/xiby/structure/string/StringMatcher.java)

### 1.2 复杂度分析

- 时间复杂度  O(n+m) n为待匹配串长度 m为模板串长度
- 空间复杂度  O(m)

## 2. 矩阵的压缩存储

### 2.1 理论依据

1. 利用三元组来存储稀疏矩阵
2. 利用三元组顺序存储稀疏矩阵时，要满足行主序排列
3. 算法思想为预先计算出每一列的第一个元素存储的位置，然后利用之前的结果可以快速的定位到每一个元素应该在的位置

### 2.2 快速转置实现

[点我查看 矩阵快速转置算法 的实现及优化](./src/main/java/tech/xiby/structure/matrix/SparseMatrix.java)

### 2.3 复杂度分析

|参考项\采用算法|  普通转置  |  快速转置  |  快速转置优化  |
|--------------|-----------|-----------|---------------|
| 时间复杂度   | O(tu * mu * nu) |O(nu + tu)|O(nu + tu)|
|空间复杂度    |  O(1)     |  O(2*nu)   |  O(nu)   |

## 2. huffman树及其应用

### 2.1 树的构造以及编解码实现

[点我查看 huffman树 的实现](./src/main/java/tech/xiby/structure/tree/HuffmanTree.java)

### 2.2 构造方法

1. 首先按照权值的升序对权值列表进行排序
2. 新建一个空的树节点，取出权值最小的两个节点，将其设为空树节点的左右孩子，权值设置为左右孩子权值之和，加入节点列表
3. 重新对权值列表进行排序
4. 重复1，2，3，直至权值列表中只有一个元素
5. 将权值列表中的第一个元素设置为树的根节点

### 2.3 编码方法

1. 设置一个栈，初始为空
2. 先序遍历这棵huffman树，如果访问节点为空，则直接返回
3. 如果访问节点为叶子节点，则栈中的数据为对应节点的haffman编码
4. 如果不为叶子节点，则左编码(访问左子树的编码)入栈
5. 先序访问左子树
6. 栈顶元素出栈
7. 右编码(访问左子树的编码)入栈
8. 线序访问右子树
9. 栈顶元素出栈

### 2.4 解码方法

1. 初始化当前节点为根节点，访问编码顺序为0
2. 如果当前节点为叶子节点，则输出编码，当前节点设置为根节点，转到7
3. 如果当前字符等于左编码，当前节点设置为当前节点的左子树
4. 如果当前字符等于右编码，当前节点设置为当前节点的右子树
5. 否则报错
6. 访问编码顺序自增
7. 判断解码是否完成，如果未完成，则转到2
8. 结束
