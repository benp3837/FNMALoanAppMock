import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Piglatin } from './piglatin';

describe('Piglatin', () => {
  let component: Piglatin;
  let fixture: ComponentFixture<Piglatin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Piglatin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Piglatin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
