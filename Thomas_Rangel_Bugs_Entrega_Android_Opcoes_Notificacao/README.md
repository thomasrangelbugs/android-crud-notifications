# Projeto Android Studio - Menu de opções + notificação por múltiplos de 10

Projeto acadêmico pronto para abrir no Android Studio.

## O que foi implementado
- menu de opções no canto superior direito com navegação entre:
  - **Listagem**
  - **Cadastro**
- persistência local em **SQLite**
- listagem dos conteúdos já inseridos no banco
- cadastro de novos conteúdos
- **notificação do sistema** sempre que um novo item é inserido e o total salvo passa a ser um número múltiplo de 10
- solicitação de permissão de notificação no Android 13+

## Estrutura principal
- `MainActivity` → tela de listagem
- `CadastroActivity` → tela de cadastro
- `BaseMenuActivity` → menu compartilhado entre as telas
- `DatabaseHelper` → banco SQLite local
- `NotificacaoHelper` → criação do canal e disparo da notificação

## Como abrir
1. Extraia o `.zip`.
2. Abra a pasta do projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Execute em emulador ou celular.

## Observação importante
O projeto foi montado com arquivos Gradle e estrutura de Android Studio. Como este ambiente não possui o `gradle-wrapper.jar`, o Android Studio pode baixar automaticamente a distribuição do Gradle na primeira sincronização.

## Onde está a regra da atividade
A regra do aviso por múltiplos de 10 está em:
- `app/src/main/java/com/thomasrangelbugs/cadastroconteudos/ui/CadastroActivity.java`
- `app/src/main/java/com/thomasrangelbugs/cadastroconteudos/utils/NotificacaoHelper.java`
