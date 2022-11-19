const url = 'https://google.com'
const search_box = '[name="q"]'
const btnAgree = '[id="L2AGLb"]'

class googlePage {
  static visit() {
    cy.visit(url);
  }

  static titleMacth(title) {
    cy.title().should('include', title)
  }

  static searchWord(keyword) {
    cy.wait(1000)
    //cy.get(btnAgree).click()
    cy.get(search_box).type(keyword)
    cy.wait(1000)
    //cy.get('[name="btnK"]').click({multiple: true})    
  }
}

export default googlePage;