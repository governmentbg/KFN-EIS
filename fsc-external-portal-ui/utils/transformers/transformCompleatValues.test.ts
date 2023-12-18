import { CompleatValue } from '../../contracts/types/serviceRequest/formComponents/fields';
import transformCompleatValues from './transformCompleatValues';

describe('transformCompleatValues', () => {
  const values: CompleatValue[] = [{ key: 1, value: 'bar' }];

  it('should returns an empty array if no arguments are passed', () => {
    expect(transformCompleatValues(undefined)).toEqual([]);
  });

  it('should returns an empty array if an empty array is passed', () => {
    expect(transformCompleatValues([])).toEqual([]);
  });

  it('should returns an array with values if a valid arguments are passed', () => {
    expect(transformCompleatValues(values)).toEqual([
      { id: '1', label: 'bar' },
    ]);
  });
});
