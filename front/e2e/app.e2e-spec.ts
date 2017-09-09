import { CoddyPage } from './app.po';

describe('coddy App', () => {
  let page: CoddyPage;

  beforeEach(() => {
    page = new CoddyPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
