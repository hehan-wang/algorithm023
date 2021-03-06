## BFS

广度优先遍历模板

```java
public static List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> res = new ArrayList<>();
  Queue<TreeNode> queue = new LinkedList<>();//利用队列FIFO的特点
  queue.add(root);//加入第一层的根节点
  while (!queue.isEmpty()) {//一层循环一次
    int size = queue.size();//取当前层的个数
    List<Integer> level = new ArrayList<>();//存当前层的节点
    for (int i = 0; i < size; i++) {
      TreeNode first = queue.poll();
      level.add(first.val);//取出当前层元素
      if (first.left != null) queue.add(first.left);//放入下一层元素
      if ((first.right != null)) queue.add(first.right);//放入下一层元素
    }
    res.add(level);
  }
  return res;
}
```



## DFS

深度优先遍历模板

```java
public static List<List<Integer>> levelorder(TreeNode root) {
  List<List<Integer>> res = new ArrayList<>();
  if (root == null) return res;
  dfs(root, 0, res);
  return res;
}

public static void dfs(TreeNode root, int level, List<List<Integer>> res) {
  if (res.size() == level) res.add(new ArrayList<>());//新增加一层的时候初始化list
  res.get(level).add(root.val);//找到当前层list 放入自己
  if (root.left != null) dfs(root.left, level + 1, res);//下钻左孩子
  if (root.right != null) dfs(root.right, level + 1, res);//下钻右孩子
}
```



## BSearch

二分搜索模板

```java
public int binarySearch(int[] array, int target) {
  int lo = 0, high = array.length - 1;
  while (lo <= high) {
    int mid = lo + (high - lo) >> 1;//防止相加超出上限
    if (array[mid] == target) return mid;
    else if (array[mid] > target) high = mid - 1;
    else lo = mid + 1;
  }
  return -1;
}
```

求上边界

```java
public int search(int[] nums, int t) {
    int n = nums.length, left = 0, right = n - 1;
    while (left < right) {
        int mid = left + right + 1 >> 1;
        if (nums[mid] <= t) left = mid;
        else right = mid - 1;
    }
    return right;
}
```

求下边界

```java
public int search(int[] nums, int t) {
    int n = nums.length, left = 0, right = n - 1;
    while (left < right) {
        int mid = left + right >> 1;
        if (nums[mid] >= t) right = mid;
        else left = mid + 1;
    }
    return right;
}
```

* 牛顿迭代求平方根推导过程 x =(x+a/x)/2

  ![image-20210127002225069](https://i.loli.net/2021/01/27/vxGmIhTlW4ePKL9.png)