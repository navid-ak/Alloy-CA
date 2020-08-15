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

fact {
	all x: Inmate | one g: Gang | x in g.members
}

assert checkSafe {
	safe implies happy
}

pred show {
	safe
}

//run show

check checkSafe
