def myQuicksort(nums):
    if len(nums)<=1:
        return nums
    pivot = nums[int(len(nums)/2)]
    s = []
#     The numbers equal to the pivot. 
    e = []
    g = []
    for i in nums:
        if i<pivot:
            s.append(i)
        elif i>pivot:
            g.append(i)
        elif i == pivot:
            e.append(i)
    s = myQuicksort(s)
    g = myQuicksort(g)
    return s+e+g
with open('input.txt', 'r') as f:
    N = f.readline()
    nums = []
    for i in range(int(N)):
        nums.append(int(f.readline()))
print(myQuicksort(nums))