import { CSS_CLASSES } from '../../../constants/cssClasses';

const showElementsByIds = (elementsIdsThatShouldBeDisplayed?: string[]) => {
  elementsIdsThatShouldBeDisplayed?.forEach((elementId: string) => {
    const element: Element | null = document.getElementById(elementId);

    if (!element?.classList.contains(CSS_CLASSES.HIDDEN)) return;

    element?.classList.remove(CSS_CLASSES.HIDDEN);
  });
};

export default showElementsByIds;
