export class Participants {
  constructor (
    public id?: number,
    public nome?: string,
    public email?: string,
    public cpf?: string,
    public cep?: string,
    public logradouro?: string,
    public bairro?: string,
    public localidade?: string,
    public uf?: string,
    public especialidade?: string,
    public senioridade?: string,
    public tecnologias?: Array<Tecnologia>,
    public comentarios?: string
  ) { }
}

export class Tecnologia {

  constructor (public nome: string

  ) {
  }
}
