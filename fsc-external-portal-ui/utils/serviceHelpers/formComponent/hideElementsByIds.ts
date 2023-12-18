import { CSS_CLASSES } from '../../../constants/cssClasses';

const hideElementsByIds = (elementsIdsThatShouldBeHidden?: string[]) => {
  elementsIdsThatShouldBeHidden?.forEach((elementId: string) => {
    const element: Element | null = document.getElementById(elementId);

    if (element?.classList.contains(CSS_CLASSES.HIDDEN)) return;

    element?.classList.add(CSS_CLASSES.HIDDEN);
  });
};

export default hideElementsByIds;
