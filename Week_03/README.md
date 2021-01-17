# 学习笔记

## 递归

本周主要学习了递归、分治、回溯。

https://u.geekbang.org/lesson/116?article=262088

1. 递归模板

```java
public void rec(int level,int max,int param){
	//terminator 终止条件
	if(level>max){
		dealResult();//处理结果
		return;
	}
	//process logic 处理逻辑
	process(level,param);
	//drill down 下钻
	rec(level+1,max,param);
	//reverse states 清除状态 一般都使用局部变量、很少使用
}
```

2. 分治模板

```java
public int devideConquer(Problem problem){
	//terminator 问题为空的时候终止
	if(problem==null){
		int res=processLastResult();
		return res;
	}
	//split 分解子问题
	SubProblem sub=splitProblem(problem);
	//drill down 分别处理子问题
	int res0=devideConquer(sub[0]);
	int res1=devideConquer(sub[1]);
	//merge 合并结果
	int result =processResult(res0,res1);
	//revert states
}
```



## 本周总结

1. 本周补了第一周的内容、还差一些练习题没有完全覆盖。
2. 学习了第三周的内容、目前还有很多练习题还没有做到 下周继续补。 
3. 下周每天早点下班、争取七点下班八点到家、回家专心刷题、每天拿出至少两个小时。
4. 继续践行“五毒神掌”、就是多练就完了

## 问题

1. 感觉刷题比较慢、精力跟不上一天也就做3道+。怎么办？