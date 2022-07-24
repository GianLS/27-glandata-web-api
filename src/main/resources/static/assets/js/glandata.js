/**
 * 
 */

if (document.getElementById("cpf")) {
	new Cleave('#cpf', {
		blocks: [3, 3, 3, 2],
		delimiters: ['.', '.', '-'],
		numericOnly: true
	})
}

if (document.getElementById("cnpj")) {
	new Cleave('#cnpj', {
		blocks: [2, 3, 3, 4, 2],
		delimiters: ['.', '.', '/', '-'],
		numericOnly: true
	})
}

if (document.getElementById("dataNascimento")) {
	new Cleave('#dataNascimento', {
		blocks: [2, 2, 4],
		delimiters: ['/', '/'],
		numericOnly: true
	})
}