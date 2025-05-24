#  Spotifei - Sistema de Gerenciamento de Músicas e Playlists

Projeto acadêmico desenvolvido em Java utilizando Swing para a interface gráfica e PostgreSQL como banco de dados relacional. O objetivo do sistema é simular uma plataforma de músicas com funcionalidades voltadas a usuários, permitindo buscas, curtidas, criação de playlists e visualização de histórico completo.

---

##  Funcionalidades Implementadas

###  Acesso e Navegação
- Login e cadastro de usuários
- Interface gráfica intuitiva via Java Swing

###  Músicas
- Busca por nome, gênero ou nome do artista
- Exibição de informações detalhadas da música

###  Playlists
- Criar, renomear e excluir playlists
- Adicionar e remover músicas de playlists

###  Curtidas
- Curtir e descurtir músicas
- Contador de curtidas por música

###  Histórico
- Histórico de buscas (com data e hora)
- Histórico de curtidas
- Histórico de descurtidas

---

##  Tecnologias Utilizadas

- **Linguagem:** Java 17
- **IDE:** NetBeans
- **Banco de Dados:** PostgreSQL
- **Interface Gráfica:** Java Swing
- **Controle de Versão:** Git + GitHub

---

##  Estrutura do Projeto

📁 src
- ┣ 📂 model → Classes modelo (Musica, Usuario, Playlist, etc.)
- ┣ 📂 dao → Classes DAO com SQL e conexão JDBC
- ┣ 📂 view → Interfaces gráficas em Swing
- ┣ 📂 util → Conexão com banco e utilitários

---

## Como Executar

1.Abra o projeto no NetBeans

2. Configure o driver JDBC "Add JAR/Folder"

3. Baixe o PostgreSQL JDBC Driver

4. Ajuste a classe Conexao.java com suas credenciais(as tabelas criadas estao no relatorio, segui o padrao do banco do moodle "Exemplo CRDU - Passo a Passo ):
   ```bash
   String url = "jdbc:postgresql://localhost:5432/spotifei";
    String usuario = "postgres";
    String senha = "sua_senha";


- Compile e execute o projeto 

- Utilize o menu inicial para registrar, logar e acessar todas as funcionalidades.

---

##  Requisitos

- Java 17
- NetBeans IDE 18+
- PostgreSQL 17
- Driver JDBC PostgreSQL

---

##  EU

- Henrique Bassan Rebechi (22.223.083-1)
- Centro Universitário FEI
- Curso: Ciência da Computação
- Projeto Acadêmico 2025 1 semestre
  
