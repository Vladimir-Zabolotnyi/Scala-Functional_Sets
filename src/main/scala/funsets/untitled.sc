import funsets.FunSets
import funsets.FunSets.*

FunSets.contains(singletonSet(1), 3)

val x = FunSets.union(singletonSet(1), singletonSet(2))
contains(x, 5)
val s1 = singletonSet(1) 
val s2 = singletonSet(2) 
val s3 = singletonSet(2) 
val difference = diff(union(s1,s2), s3)
contains(difference, 1)

val filtering = filter(union(s1,s2), s3)
