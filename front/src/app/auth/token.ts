import { Permission } from './permission';

export class Token {

  static TOKEN_KEY = 'authToken';

  raw: string;
  firstname: string;
  lastname: string;
  login: string;
  nbf: number;
  exp: number;
  permissions: Set<Permission> = new Set<Permission>();

  public static toEnum(status: string): Permission {
    return Permission[status.toUpperCase()];
  }
}
