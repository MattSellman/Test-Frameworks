Feature: Cucumber Transformations

Scenario: Correct non-zero number of books found by author
  Given I have the following books in the store with an arrayList
    | title                                | author      |
    | The Devil in the White City          | Erik Larson |
    | The Lion, the Witch and the Wardrobe | C.S. Lewis  |
    | In the Garden of Beasts              | Erik Larson |
  When I search for books by author "Erik Larson"
  Then I find "2" books

Scenario: Correct number of authors found
  Given I have the following books in the store with a Map
    | title                           | author              |
    | To kill a mockingbird           | Harper Lee          |
    | The catcher in the rye          | J.D. Salinger       |
    | The great Gatsby                | F. Scott Fitzgerald |
    | The life of Lazarillo de Tormes | [anonymous]         |
  Then I expect to have the following books
    | title                           | author              |
    | The life of Lazarillo de Tormes | [anonymous]         |
    | The great Gatsby                | F. Scott Fitzgerald |
    | To kill a mockingbird           | Harper Lee          |
    | The catcher in the rye          | J.D. Salinger       |


Scenario: Correct number of authors found
  Given I have the following books in the store with a Table Transformer
    | name                            | publisher           | quantity |
    | To kill a mockingbird           | Harper Lee          | 100      |
    | The catcher in the rye          | J.D. Salinger       | 100      |
    | The great Gatsby                | F. Scott Fitzgerald | 100      |
    | The life of Lazarillo de Tormes | [anonymous]         | 100      |
