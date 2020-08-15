sig Gang{members: set Inmate}

sig Inmate {room: Cell}

sig Cell{} 

pred safe {
	no x : Inmate , y : Inmate | one gone : Gang , gtwo : Gang | 
	((gone != gtwo) and (((x in gone.members) and (y in gtwo.members)) and x.room = y.room))
} 

pred happy {
	all x : Inmate , y : Inmate | x.room = y.room implies ~members[x] = ~members[y]
}

assert checkSafe {
	safe implies happy
}

pred show {
	happy
}

//run show

check checkSafe



