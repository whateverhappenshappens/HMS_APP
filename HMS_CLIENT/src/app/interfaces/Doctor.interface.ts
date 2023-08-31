import { DoctorSpecialization } from "../enums/DoctorSpecialization.enum";

export interface Doctor {
    id: number;
    doctorName: string;
    specialization: DoctorSpecialization;
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
      content: Doctor[];
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
  