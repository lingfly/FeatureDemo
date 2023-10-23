# jdk特性测试


### 虚拟线程

#### 性能测试
CPU密集型任务

testVirtual ComputeTask Exec time: 50263ms.

testThread ComputeTask Exec time: 50244ms.

IO密集型任务

testVirtual SleepTask Exec time: 1032ms

testThread SleepTask Exec time: 5055ms.

#### 测试synchronized

testVirtual SleepTask Exec time: 50421ms.

synchronized内包含cpu密集型任务时慢到离谱