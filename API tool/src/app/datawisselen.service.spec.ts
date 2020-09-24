import { TestBed } from '@angular/core/testing';

import { DatawisselenService } from './datawisselen.service';

describe('DatawisselenService', () => {
  let service: DatawisselenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatawisselenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
