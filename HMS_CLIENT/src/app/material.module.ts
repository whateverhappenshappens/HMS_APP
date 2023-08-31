import { NgModule } from "@angular/core";
import {MatInputModule} from "@angular/material/input"
import {MatSelectModule} from "@angular/material/select"
import {MatCardModule} from '@angular/material/card';
import {MatRadioModule} from '@angular/material/radio';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSortModule} from '@angular/material/sort'
import {MatPaginatorModule} from '@angular/material/paginator'
import {MatStepperModule} from '@angular/material/stepper'
import {MatListModule} from '@angular/material/list';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatDividerModule } from "@angular/material/divider";
import { MatButtonModule } from "@angular/material/button";
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatDrawer, MatDrawerContainer, MatSidenavModule} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';


@NgModule({
    exports:[
       MatInputModule,
       MatSelectModule,
       MatCardModule,
       MatRadioModule,
       MatCheckboxModule,
       MatSortModule,
       MatPaginatorModule,
       MatStepperModule,
       MatListModule,
       MatSnackBarModule,
       MatTableModule,
       MatDividerModule,
       MatButtonModule,
       MatToolbarModule,
       MatSidenavModule,
       MatMenuModule,
       MatIconModule,

    ]
})

export class MaterialModule{

}
