# 学习笔记

## 心得

1. 学习数据结构和算法不光可以增加内功，写出更高效的代码。更能提高逻辑思维能力。
2. 超哥分享了一些很实用的实战技巧和方法论，可以用在学习生活中。比如“五毒神掌” 重复再重复,“四步答题法” (沟通需求，选择解决方案，实现，测验)。

## HashMap小结 

https://mp.weixin.qq.com/s/lV4sp7S_c423pamK_yMadQ

* Java中HashMap 实用数组+链表/红黑树组成，当每个节点链表长度大于8的时候转化为红黑树。还有支持并发访问的ConcurrentHashMap。

* 结构 ![map](https://i.loli.net/2021/01/10/POUVBuC2TF6IoM8.png)

* put()方法

  时间复杂度O(1)
  put流程图

  ![image-20210110215450569](https://i.loli.net/2021/01/10/4rulVFOjPKE1Yki.png)

* get()方法

  时间复杂度O(1)。极限情况下，哈希冲突退化成链表时间复杂度为O(n)