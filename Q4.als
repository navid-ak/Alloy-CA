
sig Addr {}

abstract sig Name { address: set Addr+Name}

sig Alias, Group extends Name {}

fact {// Acyclic
	no n : Name | n in n.^(address)
}

fact {// at least one address
	all a : Alias | some (a.^(address) & Addr)
}

fact {// at least two levels
	some n : Name | n.address in Name
}

fact {// non-empty group
	some g : Group | some (g.~address & Name)
}

pred show {

}

fun groupMembers[] : set Name { 
	Group.~^address
}

fun emptyGroups[] : Name {
	Group - groupMembers.address
}

fun addresses[] : address {
	(Alias ->((Alias.^address) & Addr)) & ^address
}

run show 
