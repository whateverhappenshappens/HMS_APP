import { DoctorSpecialization } from "../enums/DoctorSpecialization.enum";

export interface Booking {
        patientName: string;
        patientAge: number;
        gender: string;
        bookingTime: number;
        bookingStatus: string;
        id: number;
        valid: boolean;
  }  
  interface Pageable {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    offset: number;
    pageSize: number;
    pageNumber: number;
    paged: boolean;
    unpaged: boolean;
  }
  
  interface BookingListResponse {
    bookings: {
      content: Booking[];
      pageable: Pageable;
      last: boolean;
      totalPages: number;
      totalElements: number;
      first: boolean;
      size: number;
      number: number;
      sort: {
        empty: boolean;
        sorted: boolean;
        unsorted: boolean;
      };
      numberOfElements: number;
      empty: boolean;
    };
  }
  