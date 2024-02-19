const url = "http://localhost:8080/produtos"
const productContainer = document.querySelector("#product-container")
const nome = document.querySelector("#nome")
const valor = document.querySelector("#valor")

async function getAllProduct() {
    const response = await fetch(url);

    console.log(response);

    const data = await response.json()

    console.log(data)

    data.map((product) => {
        const div = document.createElement("div")
        const nome = document.createElement("h2")
        const valor = document.createElement("h2")
        
        nome.innerText = "Produto: " + product.nome
        valor.innerText = "Preço: " + product.valor + "R$"

        div.appendChild(nome)
        div.appendChild(valor)

        productContainer.appendChild(div)
    })
}

getAllProduct();


async function cadastro() {
    const novoProduto = {
        nome: nome.value,
        valor: valor.value
    };
    
    nome.value = "";
    valor.value = "";

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(novoProduto)
        });

        if (!response.ok) {
            throw new Error('Erro ao cadastrar produto');
        }

        // Atualizar a lista de produtos após o cadastro
        productContainer.innerHTML = "";
        getAllProduct();
    } catch (error) {
        console.error("Erro ao cadastrar produto:", error);
    }
}

botao.addEventListener("click", cadastro)