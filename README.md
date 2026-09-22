# StudyFlow Dev 🚀

> **Aplicação Mobile Android para Organização e Acompanhamento de Estudos de Programação**

O **StudyFlow Dev** é um aplicativo mobile nativo para Android desenvolvido em Java, projetado para auxiliar estudantes e desenvolvedores a organizarem sua rotina de estudos técnicos. O aplicativo permite estruturar tópicos de estudo por tecnologias, acompanhar visualmente a evolução percentual de cada assunto e manter a motivação através de indicadores visuais de progresso e gamificação.

---

## 📌 Sumário

- [Visão Geral e Objetivo](#-visão-geral-e-objetivo)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades do Sistema](#-funcionalidades-do-sistema)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Banco de Dados (SQLite)](#-banco-de-dados-sqlite)
- [Pré-requisitos](#-pré-requisitos)
- [Como Instalar e Configurar](#-como-instalar-e-configurar)
- [Como Executar](#-como-executar)
- [Guia de Uso](#-guia-de-uso)
- [Autores](#-autores)

---

## 🎯 Visão Geral e Objetivo

O aprendizado de desenvolvimento de software exige consistência, prática contínua e boa gestão de temas diversificados (linguagens, bibliotecas, ferramentas e conceitos teóricos). 

O **StudyFlow Dev** centraliza essa jornada, proporcionando:
- Organização dos conteúdos de estudo agrupados por linguagem ou tecnologia;
- Controle granular do progresso percentual (0 a 100%) em cada tópico;
- Visão rápida de status no Dashboard;
- Interface intuitiva construída com componentes nativos do Android e Material Design.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando desenvolvimento nativo para a plataforma Android:

- **Linguagem:** [Java](https://www.oracle.com/java/) (Compatibilidade de bytecode configurada para Java 11)
- **Plataforma:** Android Nativo
- **Sistema de Build:** [Gradle](https://gradle.org/) 9.5.0 via Gradle Wrapper (`gradlew`)
- **Android Gradle Plugin (AGP):** 9.3.3
- **Configuração de SDK:**
  - `minSdk`: 24 (Compatível com Android 7.0 Nougat ou superior)
  - `compileSdk`: 37
  - `targetSdk`: 37
- **Bibliotecas e Componentes Android:**
  - `androidx.appcompat:appcompat:1.8.0` (Componentes de compatibilidade de interface)
  - `com.google.android.material:material:1.14.0` (Material Design 3: `BottomNavigationView`, `MaterialCardView`, `FloatingActionButton`)
  - `androidx.constraintlayout:constraintlayout:2.2.2` (Gerenciador de layouts flexíveis)
  - `androidx.activity:activity-ktx:1.13.0` (Extensões para ciclo de vida de Activities)
  - `RecyclerView`: Listagem otimizada de tecnologias e conteúdos
- **Persistência de Dados:**
  - SQLite local gerenciado nativamente através de `SQLiteOpenHelper` (banco de dados offline, autossuficiente e sem necessidade de backend externo)
- **Testes:**
  - `junit:junit:4.13.2`
  - `androidx.test.ext:junit:1.3.0`
  - `androidx.test.espresso:espresso-core:3.7.0`

---

## ✨ Funcionalidades do Sistema

### 1. Gestão de Tecnologias
- **Listagem de Tecnologias:** Exibição em cartões (`MaterialCardView`) dentro de um `RecyclerView`.
- **Cadastro de Tecnologias:** Criação dinâmica de novas tecnologias via modal interativo (`AlertDialog`), com validação de campos obrigatórios.
- **Carga Inicial (Seeds):** O aplicativo já inicializa automaticamente com duas tecnologias cadastradas para experimentação imediata: *"Java"* e *"Android Nativo"*.

### 2. Gestão de Conteúdos por Tecnologia
- **Navegação Contextual:** Ao clicar sobre uma tecnologia, o aplicativo abre uma tela dedicada aos conteúdos específicos daquela tecnologia.
- **Cadastro de Conteúdos:** Permite cadastrar tópicos de estudo informando título e descrição opcional.
- **Relacionamento 1:N:** Cada conteúdo é rigidamente vinculado à sua tecnologia correspondente via chave estrangeira.

### 3. Acompanhamento de Progresso
- **Indicador Visual:** Cada conteúdo possui uma barra de progresso horizontal (`ProgressBar`) e um indicador em porcentagem.
- **Ajuste Interativo:** Ao tocar sobre qualquer conteúdo, abre-se um diálogo com uma barra deslizante (`SeekBar`) que permite ajustar o progresso de 0% a 100% em tempo real.

### 4. Navegação por Abas (Bottom Navigation)
- **Dashboard:** Tela inicial com cartões de indicadores de gamificação:
  - Pontos de Experiência (XP);
  - Sequência de Estudos (dias seguidos / streak);
  - Desafios Concluídos.
- **Tecnologias:** Central principal de navegação e gestão de tecnologias e conteúdos.
- **Metas:** Seção dedicada ao planejamento de metas de estudo (placeholder com informativo de atualização futura).
- **Perfil:** Visualização de informações do estudante (avatar padrão, nome de usuário, e-mail, nível e ranking).

---

## 📂 Estrutura do Projeto

O código-fonte segue a estrutura padrão de projetos Android com separação por pacotes de responsabilidade (`data`, `model`, `ui`, `ui/adapter`):

```text
StudyFlowDev/
├── .idea/                                # Configurações da IDE Android Studio
├── app/
│   ├── build.gradle.kts                  # Configurações de compilação do módulo do app
│   ├── src/
│   │   ├── androidTest/                  # Testes instrumentados (Espresso / AndroidJUnit)
│   │   │   └── java/com/example/studyflowdev/ExampleInstrumentedTest.java
│   │   ├── test/                         # Testes unitários locais (JUnit 4)
│   │   │   └── java/com/example/studyflowdev/ExampleUnitTest.java
│   │   └── main/
│   │       ├── AndroidManifest.xml       # Manifesto da aplicação (Activities, permissões e tema)
│   │       ├── java/com/example/studyflowdev/
│   │       │   ├── MainActivity.java     # Activity principal e gerenciador do BottomNavigationView
│   │       │   ├── data/
│   │       │   │   └── DatabaseHelper.java # Gerenciador do banco SQLite e operações CRUD
│   │       │   ├── model/
│   │       │   │   ├── Conteudo.java     # Entidade do tópico de estudo
│   │       │   │   └── Tecnologia.java   # Entidade da tecnologia
│   │       │   └── ui/
│   │       │       ├── ConteudosActivity.java    # Tela de conteúdos de uma tecnologia
│   │       │       ├── DashboardFragment.java    # Aba do Dashboard
│   │       │       ├── MetasFragment.java        # Aba de Metas
│   │       │       ├── PerfilFragment.java       # Aba de Perfil
│   │       │       ├── TecnologiasFragment.java  # Aba de Tecnologias
│   │       │       └── adapter/
│   │       │           ├── ConteudoAdapter.java   # Adapter para RecyclerView de conteúdos
│   │       │           └── TecnologiaAdapter.java # Adapter para RecyclerView de tecnologias
│   │       └── res/                      # Recursos visuais e interfaces XML
│   │           ├── drawable/             # Vetores e ícones das abas
│   │           ├── layout/               # Arquivos XML de layout e diálogos
│   │           │   ├── activity_main.xml
│   │           │   ├── activity_conteudos.xml
│   │           │   ├── fragment_dashboard.xml
│   │           │   ├── fragment_tecnologias.xml
│   │           │   ├── fragment_metas.xml
│   │           │   ├── fragment_perfil.xml
│   │           │   ├── item_tecnologia.xml
│   │           │   ├── item_conteudo.xml
│   │           │   ├── dialog_add_conteudo.xml
│   │           │   └── dialog_update_progresso.xml
│   │           ├── menu/                 # Menu da barra inferior (navigation_menu.xml)
│   │           ├── values/               # Cores, strings e temas (Material 3)
│   │           │   ├── colors.xml
│   │           │   ├── strings.xml
│   │           │   └── themes.xml
│   │           └── xml/                  # Regras de backup e extração de dados
├── gradle/
│   ├── libs.versions.toml                # Catálogo de versões de dependências (Version Catalog)
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties     # Configuração da versão do Gradle (9.5.0)
├── build.gradle.kts                      # Script de build da raiz do projeto
├── settings.gradle.kts                   # Definição de repositórios e módulos
├── gradlew                               # Script executável do Gradle para Linux/macOS
├── gradlew.bat                           # Script executável do Gradle para Windows
└── README.md                             # Documentação principal do repositório
```

---

## 🗄️ Banco de Dados (SQLite)

O aplicativo armazena todos os seus dados em um banco de dados local **SQLite** (`studyflow.db`, versão 1), gerenciado pela classe `DatabaseHelper`.

### Diagrama Lógico e Esquema DDL

```sql
-- Tabela de Tecnologias
CREATE TABLE tecnologia (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL
);

-- Tabela de Conteúdos
CREATE TABLE conteudo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tecnologia_id INTEGER NOT NULL,
    titulo TEXT NOT NULL,
    descricao TEXT,
    progresso INTEGER DEFAULT 0,
    FOREIGN KEY(tecnologia_id) REFERENCES tecnologia(id)
);
```

### Operações Suportadas
- **`insertTecnologia(Tecnologia)`**: Insere uma nova linguagem/tecnologia.
- **`getAllTecnologias()`**: Recupera todas as tecnologias para exibição na lista.
- **`insertConteudo(Conteudo)`**: Insere um tópico de estudo com referência à tecnologia pai.
- **`getConteudosPorTecnologia(long tecnologiaId)`**: Retorna apenas os conteúdos pertencentes à tecnologia selecionada.
- **`updateProgressoConteudo(long conteudoId, int novoProgresso)`**: Atualiza o valor percentual (0 a 100) do conteúdo.

---

## 📋 Pré-requisitos

Para compilar, executar e testar o projeto em seu ambiente local, certifique-se de possuir:

1. **Git** instalado no sistema operacional.
2. **Android Studio** (versão Ladybug, Koala, Jellyfish ou superior recomendada).
3. **Java Development Kit (JDK):** Versão 11 ou superior (geralmente embutida com o Android Studio).
4. **Android SDK:**
   - `compileSdk`: 37
   - `minSdk`: 24
   - Ferramentas de compilação e plataforma atualizadas através do SDK Manager.
5. **Dispositivo de Execução:**
   - Emulador Android (AVD) com Android 7.0 (API 24) ou superior; **ou**
   - Dispositivo físico Android conectado via cabo USB com a opção **Depuração USB** habilitada.

---

## 🚀 Como Instalar e Configurar

1. **Clonar o Repositório:**
   ```bash
   git clone https://github.com/xLemos7/StudyFlowDev.git
   ```

2. **Abrir o Projeto no Android Studio:**
   - Inicie o **Android Studio**;
   - Clique em **File** > **Open...** (ou na tela inicial, clique em **Open**);
   - Navegue até a pasta onde clonou o projeto (`StudyFlowDev`) e selecione-a;
   - Aguarde o Android Studio realizar a leitura dos arquivos e o download dos plugins necessários.

3. **Sincronizar o Gradle:**
   - O Android Studio deverá iniciar automaticamente o processo de sincronização (`Gradle Sync`);
   - Caso não inicie automaticamente, clique no ícone **Sync Project with Gradle Files** (ícone de elefante com seta) ou acerte pelo menu **File** > **Sync Project with Gradle Files**.

> **Nota de Configuração:** O aplicativo não requer variáveis de ambiente (`.env`), contas em nuvem ou chaves de API externas. O banco de dados SQLite é criado localmente no dispositivo/emulador no primeiro início do aplicativo.

---

## 📱 Como Executar

### Opção 1: Diretamente pelo Android Studio (Recomendado)

1. Com o projeto aberto e sincronizado, selecione o dispositivo de destino na barra de ferramentas superior (um emulador AVD ou seu smartphone físico conectado);
2. Certifique-se de que o módulo selecionado é `app`;
3. Clique no botão verde de execução **Run 'app'** (ou utilize o atalho `Shift + F10` no Windows/Linux ou `Ctrl + R` no macOS);
4. O app será compilado, instalado e inicializado automaticamente na tela do dispositivo.

### Opção 2: Linha de Comando (Terminal)

Você também pode utilizar o Gradle Wrapper a partir da raiz do projeto:

- **No Windows (PowerShell / Prompt de Comando):**
  ```powershell
  # Compilar o APK em modo Debug
  .\gradlew.bat assembleDebug

  # Instalar o aplicativo diretamente no dispositivo/emulador conectado
  .\gradlew.bat installDebug
  ```

- **No Linux ou macOS:**
  ```bash
  # Dar permissão de execução ao script (se necessário)
  chmod +x ./gradlew

  # Compilar o APK em modo Debug
  ./gradlew assembleDebug

  # Instalar no dispositivo conectado
  ./gradlew installDebug
  ```

*O arquivo APK gerado estará disponível no diretório: `app/build/intermediates/apk/debug/app-debug.apk`.*

---

## 💡 Guia de Uso

### 1. Navegação Principal
A barra de navegação inferior (`BottomNavigationView`) contém 4 opções:
- **Dashboard:** Visualize suas informações gerais de desempenho e motivação.
- **Tecnologias:** Tela operacional para gerenciar o que você está estudando.
- **Metas:** Informações sobre planejamento e objetivos.
- **Perfil:** Dados da conta de estudante.

### 2. Cadastrar uma Nova Tecnologia
1. Vá até a aba **Tecnologias**;
2. Toque no botão flutuante **`+`** (canto inferior direito);
3. Digite o nome da tecnologia desejada (exemplo: `Kotlin`, `Python`, `Flutter`, `SQL`);
4. Toque em **Salvar**;
5. A nova tecnologia aparecerá instantaneamente na lista.

### 3. Gerenciar Conteúdos de uma Tecnologia
1. Na aba **Tecnologias**, toque sobre o cartão da tecnologia que deseja estudar (exemplo: `Java`);
2. Você será direcionado para a tela com o título da tecnologia selecionada;
3. Para adicionar um novo tópico de estudo, toque no botão flutuante **`+`**;
4. Preencha o **Título** (ex.: `Orientação a Objetos - Herança e Polimorfismo`) e a **Descrição** (opcional, ex.: `Estudar classes abstratas e interfaces`);
5. Toque em **Salvar**.

### 4. Atualizar o Progresso de Estudo
1. Na lista de conteúdos de uma tecnologia, toque sobre o card do conteúdo que você estudou;
2. Na janela que se abrir, deslize a barra (**SeekBar**) para definir a nova porcentagem concluída (de 0% a 100%);
3. Toque em **Salvar**;
4. O cartão do conteúdo será atualizado imediatamente com a nova porcentagem e a barra colorida correspondente.

---

## 👥 Autores

Projeto desenvolvido no âmbito acadêmico para acompanhamento e organização de estudos de desenvolvimento de software:

- **Ana Clara Lemos da Silva** — [GitHub: @xLemos7](https://github.com/xLemos7)

---

Desenvolvido para fins de aprendizado e aprimoramento da jornada em tecnologia! 🚀
