export function americanToBrazilianDate(date) {
  const data = new Date(date);
  const dia = data.getDate().toString().padStart(2, "0");
  const mes = (data.getMonth() + 1).toString().padStart(2, "0"); // note que é necessário adicionar 1 ao mês, já que o método getMonth() retorna valores de 0 a 11
  const ano = data.getFullYear().toString();
  const dataFormatada = `${dia}/${mes}/${ano}`;
  return dataFormatada;
}
