sig Gang{members: set Inmate}

sig Inmate {room: Cell}

sig Cell{} 

pred safe {
	no x : Inmate , y : Inmate | some gone: Gang , gtwo: Gang | 
	((gone != gtwo) and (((x in gone.members) and (y in gtwo.members)) and x.room = y.room))
} 

pred show {
	safe 	not safe
}


run show
