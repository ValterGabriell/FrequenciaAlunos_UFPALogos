<h1 align="center">Sistema de frequência para o cursinho Logos da UFPA.</h1>

##  Sobre esse projeto
Este é um projeto que deve automatizar o sistema de frequência de alunos no cursinho gratuíto da UFPA.


## Indíce
<!--ts-->
   * [Como usar?](#como-usar)
   * [Endpoints](#endpoints)
   * [Testes](#testes)
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
	"message": "Frequência para brito válidada! - Dia: 2023-04-21"
}
```

<h2>Justificar falta e adicionar o dia na frequencia do estudante</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/frequency</td>
    <td>realizar a validação de frequencia de um estudante</td>
    <td>studentId</td>
    <td>date</td>
  </tr> 
  </table>
 

<h3>Resposta esperada</h3></br>

```bash
{
	"message": "Frequência para brito justificada! - Dia: 2023-04-21"
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
[
	{
		"cpf": "42345678912",
		"username": "silva"
	},
	{
		"cpf": "12345678912",
		"username": "gabriel"
	},
	{
		"cpf": "32345678912",
		"username": "gabriel"
	},
	{
		"cpf": "62345678912",
		"username": "brito"
	}
]

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
	"studentId": "62345678912",
	"daysListThatStudentGoToClass": [
		{
			"date": "2023-04-21",
			"justified": false
		},
		{
			"date": "2023-04-15",
			"justified": true
		}
	]
}

Quando justified for false, significa que o aluno foi para a aula no dia em questão, quando for true, significa que o aluno faltou, porém a sua falta foi justificada.

```



<h2>Exportar tabela do excel</h2>
<p>No momento a tabela é exportada apenas para a máquina, sendo salva na pasta C:/teste/teste.xls. É necessário criar esse caminho e arquivo para funcionar</p>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/frequency/sheet</td>
    <td>Exporta tabela do dia atual</td>
  </tr>
</table>

<h3>Resposta esperada</h3></br>

```
OK! 

verificar na pasta se um arquivo chamado "teste" foi criado, além do que você criou

```


<h2>Exportar tabela do excel para dia específico</h2>
<p>No momento a tabela é exportada apenas para a máquina, sendo salva na pasta C:/teste/teste.xls. É necessário criar esse caminho e arquivo para funcionar</p>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/frequency/sheet</td>
    <td>Exporta tabela do dia específico</td>
    <td>date (AAAA-MM-DD)</td>
  </tr>
</table>

<h3>Resposta esperada</h3></br>

```
OK! 

verificar na pasta se um arquivo chamado "teste" foi criado, além do que você criou

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




<h1>Testes</h1>

<h3>O nome de usuário não pode ser nulo</h3></br>

```
   @Test
    @DisplayName("A username should be not null and return true when it is")
    void isUsernameNotNull_ReturnTrue_WhenUsernameIsNotNull() {
        Assertions.assertTrue(studentUsernameTest.usernameIsNull());
    }
    

    
        public boolean usernameIsNull() {
        boolean isUsernameNotNull = !getUsername().isEmpty() && !getUsername().isBlank();
        if (!isUsernameNotNull){
            throw new RequestExceptions(ExceptionsValues.USERNAME_NULL);
        }
        return true;
    }
    
```


<h3>O nome de usuário precisa ter mais de 2 caracteres</h3></br>

```
    @Test
    @DisplayName("A username should have more than 2 characters and return true when it is")
    void isUsernameBiggerThan2Chars() {
        Assertions.assertTrue(studentUsernameTest.usernameHasToBeMoreThan2Chars());
    }

    

    
  public boolean usernameHasToBeMoreThan2Chars() {
        boolean isUsernameLenghtOk = getUsername().length() > 2;
        if (!isUsernameLenghtOk){
            throw new RequestExceptions(ExceptionsValues.USERNAME_ILLEGAL_LENGHT);
        }
        return true;
    }
```



<h3>O nome de usuário precisa ter apenas letras</h3></br>

```
    @Test
    @DisplayName("A username should have only letters and no numbers")
    void isUsernameOnlyLetters() {
        Assertions.assertTrue(studentUsernameTest.usernameHasContainsOnlyLetters());
    }


    

    
    public boolean usernameHasContainsOnlyLetters() {
        String regex = "^[a-zA-Z]+$";
        boolean isUsernameLenghtOk = getUsername().matches(regex);
        if (!isUsernameLenghtOk){
            throw new RequestExceptions(ExceptionsValues.USERNAME_ILLEGAL_CHARS);
        }
        return true;
    }
```


<h3>O CPF precisa ter exatamente 11 digitos</h3></br>

```
    @Test
    @DisplayName("cpf should have exactly 11 characters and return true when it is")
    void cpfLenght() {
        Assertions.assertTrue(studentCpfTest.isCpfHave11chars());
    }
    
    public boolean isCpfHave11chars() {
        boolean isCpfLenghtOk = getCpf().length() == 11;
        if (!isCpfLenghtOk){
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        return true;
    }
```


<h3>O CPF não pode ser nulo</h3></br>

```
    @Test
    @DisplayName("A cpf should be not null and return true when it is")
    void isCpfNotNull_ReturnTrue_WhenUsernameIsNotNull() {
        Assertions.assertTrue(studentCpfTest.cpfIsNull());
    }

    
    public boolean cpfIsNull() {
        boolean isUsernameNotNull = !getCpf().isEmpty() && !getCpf().isBlank();
        if (!isUsernameNotNull){
            throw new RequestExceptions(ExceptionsValues.CPF_NULL);
        }
        return true;
    }
```








<h1>Créditos</h1>

---

<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)

