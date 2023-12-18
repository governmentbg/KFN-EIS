//KEEP IT FOR FUTURE USE

import { Modal, ModalNames } from '../store/modal';

export default (modals: Modal[], name: ModalNames): boolean =>
  !!modals?.find((modal) => modal?.name === name)?.isOpen === true;
