# Teste SOA CVC


O teste é simples porem pratico, pois você verá muito disso caso você seja aprovado pela CVC.

# Descrição do Teste

Vamos supor que a CVC necessida realizar uma conexão com um novo broker de hoteis.

Porem não gostaríamos que nosso frontend conecta-se diretamente esse broker.

Sendo assim precisamos que você construa uma api para intermediar o acesso entre nosso front e o broker final.

### Api do nosso parceiro:

Consulta de Hotel por código da cidade:

**URL: https://cvcbackendhotel.herokuapp.com/hotels/avail/{ID_da_Cidade}**

**Method: GET**

***A API responde os seguintes ID_da_Cidade:***

1. ***1032*** (Porto Seguro)
2. ***7110*** (Rio de Janeiro)
3. ***1032*** (São Paulo)

***Exemplo:***
> **URL: https://cvcbackendhotel.herokuapp.com/hotels/avail/1032**

```json
// Response
[{
  "id": 1,
  "name": "Hotel Teste 1",
  "cityCode": 1032,
  "cityName": "Porto Seguro",
  "rooms": [
    {
      "roomID": 0,
      "categoryName": "Standard",
      "price": {
        "adult": 1372.54,
        "child": 848.61
      }
    }
  ]
}]
```

Detalhes do Hotel por código de hotel:

**URL: https://cvcbackendhotel.herokuapp.com/hotels/{ID_Do_Hotel}**

**Method: GET**

***Exemplo:***
> **URL: https://cvcbackendhotel.herokuapp.com/hotels/1**


```json
// Response
[{
  "id": 1,
  "name": "Hotel Teste 1",
  "cityCode": 1032,
  "cityName": "Porto Seguro",
  "rooms": [
    {
      "roomID": 0,
      "categoryName": "Standard",
      "price": {
        "adult": 1372.54,
        "child": 848.61
      }
    }
  ]
}]
```
# Teste 1:

Nosso frontend não faz nenhuma regra de negócio, sendo assim precisamos que o serviço já traga o valor da viagem do passageiro para ser exibida.

1. Construa uma API Rest no barramento 12C com os parametros de entrada:
2. O serviço do broker retorna sempre o valor por dia, sendo assim precisamos calcular o quanto vai custar a viagem para cada um dos quartos retornados

**Detalhes do calculo da viagem**
Como o broker só retorna o valor da diaria precisamos pegar os valores de checkin e checkout que recebemos em nosso serviço e calcular o valor total da viagem adicionando a comissão da CVC ex:

  1. Valor do broker 100 o cliente quer ficar 5 dias totalPrice = 500,00
  2. Para todos os preços adicionar 30% no valor formula:
    * Valor da diária 100,00 total de dias 5 Formula: ((100*5)/0.7) = 714,28.

**URL: Construa a URL como achar melhor
**Method: GET**

***Parametros obrigatórios:***
1. CityCode
2. Checkin
3. Checkout

**Resposta do Serviço**

```json
{
  "id": 1,
  "cityName": "Porto Seguro",
  "rooms":[{
      "roomID": 1,
      "categoryName": "Standard",
      "totalPrice": 10000.00,
      "priceDetail": {
        "pricePerDayAdult": 500.00,
        "pricePerDayChild": 50.00
      }
    }]
}
```


# Teste 2:

Construir um serviço que retorne os detalhes do hotel utilizando a mesma regra acima porem passando o id do hotel que gostaria de consulta.

Utilizar o serviço ***"Detalhes do Hotel por código de hotel"*** descrito na explicação acima.

**URL: Construa a URL como achar melhor
**Method: GET**

***Parametros obrigatórios:***
1. HotelID

O Hotel ID é o ID do serviço retornado na url:
> **https://cvcbackendhotel.herokuapp.com/hotels/avail/1032**

**Resposta do Serviço**

```json
{
  "id": 1,
  "cityName": "Porto Seguro",
  "rooms":[{
      "roomID": 1,
      "categoryName": "Standard",
      "totalPrice": 10000.00,
      "priceDetail": {
        "pricePerDayAdult": 500.00,
        "pricePerDayChild": 50.00
      }
    }]
}
```


# Observações

Lembre do que falamos na entrevista a que a CVC presa muito a ***VELOCIDADE*** e qualquer segundo é importante, sendo assim te damos alguns conselhos:
>1. Construa uma API com o máximo de performance possível
>2. Tente utilizar algum dos processos paralelo assim você pode ganhar tempo na resposta. ;-)

Construa os dois serviço e faça o deploy em um repositório GitHub ou nos envie por e-mail.

Qualquer dúvida nos avise.

# Muito Boa Sorte e estamos ansiosos para receber seu código.
