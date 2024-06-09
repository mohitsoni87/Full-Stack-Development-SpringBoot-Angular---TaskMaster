import { TestBed } from '@angular/core/testing';

import { HttpConnectorService } from './http-connector.service';

describe('HttpConnectorService', () => {
  let service: HttpConnectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpConnectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
