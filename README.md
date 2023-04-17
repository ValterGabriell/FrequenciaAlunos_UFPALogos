<h1 align="center">Sistema de frequência para o cursinho Logos da UFPA.</h1>

##  Sobre esse projeto
Este é um projeto que deve automatizar o sistema de frequência de alunos no cursinho gratuíto da UFPA.


## Indíce
<!--ts-->
   * [Como usar?](#como-usar)
   * [Endpoints](#endpoints)
   * [Creditos](#creditos)
<!--te-->
  
<h1>Como usar?</h1>
<p>Clonar o projeto, rodar o projeto!</p>
  
<h1>Endpoints</h1>
<h3>BASE URL</h3>

```bash
http://localhost:8080/
``` 
<h1>POST</h1></br>

<h2>Cadastrar estudante</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/students</td>
    <td>realizar inserção de um estudante no Database</td>
  </tr> 
  </table>
  
  <h3>Request esperada</h3></br>

```bash
{
	"cpf":"233456789",
	"username":"name"
}
```

<h3>Resposta esperada</h3></br>

```bash
{
	"cpf": "233456789",
	"username": "valter",
	"frequency": {
		"id": "233456789"
	}
}
```


<h2>Validar frequencia do estudante</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
    
  </tr>
  <tr>
    <td>/frequency</td>
    <td>realizar a validação de frequencia de um estudante</td>
    <td>studentId</td>
  </tr> 
  </table>
 

<h3>Resposta esperada</h3></br>

```bash
{
	"cpf": "233456789",
	"username": "valter",
	"frequency": {
		"id": "233456789"
	}
}
```



<h1>GET</h1></br>


<h2>Recuperar todos os estudantes</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/students/get-all</td>
    <td>Retorna todos os estudantes</td>
  </tr>
</table>

<h3>Resposta esperada</h3></br>

```
todo

```

<h2>Recuperar os dias que o estudante viu aula</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/frequency</td>
    <td>Retorna os dias que o estudante viu aula</td>
    <td>studentId</td>
  </tr>
</table>

<h3>Resposta esperada</h3></br>

```
{
	"studentId": "233456789",
	"frequencyId": "233456789",
	"daysListThatStudentGoToClass": [
		{
			"date": "2023-04-12"
		}
	]
}

```


<h2>Gerar QRCode para validação</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/qrcode/generate</td>
    <td>Retorna o QRcode que representa o ID do estudante</td>
    <td>studentId</td>
  </tr>
</table>

<h3>Resposta esperada</h3></br>

![image](https://user-images.githubusercontent.com/63808405/232524532-3a0ce398-9446-4969-b300-fcfcb28d60e0.png)

</br>

<h1>Créditos</h1>

---

<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)

